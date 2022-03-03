import {FormEvent, useEffect, useState} from "react";
import {ItemStructure} from "./model";
import ToDoItem from "./ToDoItem";
import { useTranslation } from "react-i18next";


export default function ToDoApp(){
    const [name, setName] =useState('');
    const [searchname, setsearchName] =useState('');// nach Name suchen lassen
    const [description, setDescription] = useState('');
    const [zeitraum, setZeitraum] = useState('');
    const [searchzeitraum, setsearchZeitraum] = useState('');
    const [allData, setAllData] = useState([] as Array <ItemStructure>)
    const { t } = useTranslation();


    useEffect( () => {
        getAllData()
    }, []
    )


  const getAllData = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/getallitems`)
            .then(response => response.json())
            .then((response2 : Array<ItemStructure>) => {setAllData(response2)})

    }

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
                    'Content-Type': 'application/json'
                }
            }).then(() => getAllData())
                .then(() => {
                    setName('')
                    setDescription('')
                    setZeitraum('')
                })
        }
    }

    const deletechecked = () => {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/checkeditems`, {
                method: 'PUT'
            }).then(response => response.json())
                .then((todosFromBackend: Array<ItemStructure>) => setAllData(todosFromBackend));
        }


//
    return(
        <div>
        <div>
            <div  >
                <form className="create" onSubmit={ev =>postData(ev)}>
            <h3>{t("createItem")}</h3>
            <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderName")} value={name} onChange={e => setName(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderDescription")} value={description} onChange={e => setDescription(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderDeadline")} value={zeitraum} onChange={e => setZeitraum(e.target.value)}/></div>
                <button className="button" type='submit'>{t('buttonCreateItem')}</button>
                </form>
            </div>
            <div className="create">
            <h3>{t('searchItem')}</h3>
            <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderName")} value={searchname} onChange={e => setsearchName(e.target.value)} /></div>
                <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderDeadline")} value={searchzeitraum} onChange={e => setsearchZeitraum(e.target.value)}/></div>
            </div>
        </div>
            <div className="lowernavbar">

                <button className="button" onClick={getAllData}>{t("buttonGetAllData")}</button>
                <button className="button" onClick={deletechecked}>{t("buttonDeleteChecked")}</button>

            </div>

        <div className="itemlist">
        {
            allData.length >0
                ?
                allData.filter(e => e.name.toLowerCase().includes(searchname.toLowerCase()))
                    .filter(e => e.formattedEndDate.includes(searchzeitraum))
                    .map(e => <ToDoItem item ={e} key={e.id} getData={getAllData}/>)
                :
                <div>{t('errorNoDataOrLoading')}</div>
        }
        </div>
        </div>

)
}