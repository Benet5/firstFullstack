import {useEffect, useState} from "react";
import {ItemStructure} from "./model";
import {Link, useNavigate, useParams} from "react-router-dom";
import {t} from "i18next";
import ToDoItem from "./ToDoItem";
import {setFlagsFromString} from "v8";


export default function EditItem() {
    const [item, setItem] = useState({} as ItemStructure)
    const params = useParams();
    const [errorMessage, setErrormessage] = useState('')

    // mit higher Order functions übergeben?
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [deadline, setDeadline] = useState('');


//usenavigate hook
    const navigate = useNavigate()

    useEffect(() => {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/getitembyid/${params.itemId}`)
                .then(response => response.json())
                .then((item: ItemStructure) => {
                    setName(item.name)
                    setDescription(item.description)
                    setDeadline(item.formattedEndDate)
                    setItem(item)
                })
        }, [params.itemId]
    )

    const savetoEdit = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/updateitem/${params.itemId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    name: name,
                    description: description,
                    deadline: deadline,
                    id :item.id,
                    status :item.status
                }
            )
        }).then(() => navigate("/todoapp"))
    };



return (
    <div className={item.status ? "todoitem1" : "todoitem2"} data-testid="the-item">
        <div className={errorMessage.length > 2 ? "error" : ""} data-testid="errorItemedit">
            {errorMessage}
        </div>
        <div className={item.status ? "check1" : "check2"}>
            <div className={item.status ? "check1" : "check2"}>{t('itemName')}
                <input className="input" type={'text'} placeholder={t("inputPlaceholderName")} value={name} onChange={e => setName(e.target.value)}/>
            </div>
            <div className={item.status ? "check1" : "check2"}>{t('itemDescription')}
                <input className="input" type={'text'} placeholder={t("inputPlaceholderDescription")} value={description} onChange={e => setDescription(e.target.value)}/>
            </div>
            <div className={item.status ? "check1" : "check2"}> {t('itemDeadline')}
                <input className="input" type={'text'} placeholder={t("inputPlaceholderDeadline")} value={deadline} onChange={e => setDeadline(e.target.value)}/>
            </div>
            <div className={item.status ? "check1" : "check2"}>{t('itemID')} {item.id}</div>
            <div
                className={item.status ? "check1" : "check2"}>{t('itemStatus')} {item.status ? t('todoChecked') : t('todoOpen')}</div>
        </div>
        <div className={"buttonBack"}>
            <button className="button" data-testid="savebuttontest" onClick={savetoEdit}>{t('buttonSaveItem')}</button>

        </div>

    </div>

)

}