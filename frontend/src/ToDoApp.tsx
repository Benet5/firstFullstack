import {FormEvent, useCallback, useEffect, useState} from "react";
import {ItemStructure} from "./model";
import ToDoItem from "./ToDoItem";
import { useTranslation } from "react-i18next";
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "./AuthProvider";



export default function ToDoApp(){
    const [name, setName] =useState('');
    const [description, setDescription] = useState('');
    const [zeitraum, setZeitraum] = useState('');
    const [searchname, setsearchName] =useState(localStorage.getItem('currentsearchname') ?? '');
    const [searchzeitraum, setsearchZeitraum] = useState(localStorage.getItem('currentsearchzeit') ?? '');
    const [allData, setAllData] = useState([] as Array <ItemStructure>)
    const { t } = useTranslation();
    const [errorMessage, setErrorMessage] = useState('')
    const navigate =useNavigate()
    const {token, logout} = useAuth()



    useEffect( () => {
        if(token.length<3) {
            navigate("/todoapp/auth/login")}
        else {
            localStorage.setItem('currentsearchname', searchname);
            localStorage.setItem('currentsearchzeit', searchzeitraum);
            setErrorMessage('')
            getAllData()
        }
    }, [searchname, searchzeitraum, navigate, token, ()=>getAllData]
    )


  const getAllData = useCallback(() => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/getallitems`,
            {headers: {
            Authorization: `Bearer ${token}`}})
            .then(response => {
                if(response.ok){
                    return response.json();}
                throw new Error()
            })
            .then((response2 : Array<ItemStructure>) => {setAllData(response2)})
            .catch(e => setErrorMessage(e.message));
    },[token]);

    const postData = (event : FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        if (name.length > 2) {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp`, {
                method: 'POST',
                body: JSON.stringify({
                    name: name,
                    description: description,
                    zeitraum: zeitraum
                }),
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                }
            })
            .then (response => {if (!response.ok) throw new Error()} )
            .then(() => getAllData())
            .then(() => {
                    setName('')
                    setDescription('')
                    setZeitraum('')
                    setErrorMessage('')
                })
            .catch(e =>setErrorMessage(e.message))
        }
    }

    const deletechecked = () => {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/checkeditems`, {
                method: 'PUT',
                headers:{
                    Authorization: `Bearer ${token}`,
                }
            })
                .then(response => {
                if(response.ok){
                    return response.json();}
                console.error("Failed to delete checked items")
                throw new Error()
            })
                .then((todosFromBackend: Array<ItemStructure>) => setAllData(todosFromBackend))
                .catch(e => setErrorMessage(e.message));
        }

        const logoutPage= () => {
        logout()
        }


//
    return (
        <div>
            <div>
                <div className={errorMessage.length > 2 ? "error" : ""} data-testid={"errorItemApp"}>
                    {errorMessage}
                </div>
                {token &&
                    <div className="flex">
                        <Link to="/todoapp" className=""><h3>Alle ToDos!</h3></Link>
                        <Link to="/todoapp/auth/logout" onClick={logoutPage}><h3>Logout</h3></Link>
                    </div>
                }


                <div>
                    <form className="create" onSubmit={ev => postData(ev)}>
                        <h3>{t("createItem")}</h3>
                        <div><input className="input" type={'text'} placeholder={t("inputPlaceholderName")} value={name}
                                    onChange={e => setName(e.target.value)}/></div>
                        <div><input className="input" type={'text'} placeholder={t("inputPlaceholderDescription")}
                                    value={description} onChange={e => setDescription(e.target.value)}/></div>
                        <div><input className="input" type={'text'} placeholder={t("inputPlaceholderDeadline")}
                                    value={zeitraum} onChange={e => setZeitraum(e.target.value)}/></div>
                        <button className="button" type='submit'>{t('buttonCreateItem')}</button>
                    </form>

                </div>
                <div className="create">
                    <h3>{t('searchItem')}</h3>
                    <div><input className="input" type={'text'} placeholder={t("inputPlaceholderName")}
                                value={searchname} onChange={e => setsearchName(e.target.value)}/></div>
                    <div><input className="input" type={'text'} placeholder={t("inputPlaceholderDeadline")}
                                value={searchzeitraum} onChange={e => setsearchZeitraum(e.target.value)}/></div>
                </div>
            </div>
            <div className="lowernavbar">

                <button className="button" onClick={getAllData}>{t("buttonGetAllData")}</button>
                <button className="button" data-testid={"deleteCheckedbuttontest"}
                        onClick={deletechecked}>{t("buttonDeleteChecked")}</button>

            </div>

            <div className="itemlist">
                {
                    allData.length > 0
                        ?
                        allData.filter(e => e.name.toLowerCase().includes(searchname.toLowerCase()))
                            .filter(e => e.formattedEndDate.includes(searchzeitraum))
                            .map((e, index) => <div data-testid={"items"} key={e.id}><ToDoItem item={e} key={e.id} getData={getAllData}/>
                            </div>)
                        :
                        <div>{t('errorNoDataOrLoading')}</div>
                }
            </div>
        </div>

    )
}