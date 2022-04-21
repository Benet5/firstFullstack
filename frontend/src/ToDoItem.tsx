import {ItemStructure} from "./model";
import { useTranslation } from "react-i18next";
import {useState} from "react";
import {Link} from "react-router-dom";
import {useAuth} from "./AuthProvider";

interface ToDoItemprops{
    item: ItemStructure
    getData: () => void;
}

export default function ToDoItem(prop: ToDoItemprops) {
    const { t } = useTranslation();
    const [errorMessage, setErrorMessage] = useState('')
    const {token} =useAuth();
    //const [token] = useState(localStorage.getItem('jwt') ?? '')
    //test

    const checkitem = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/checkitemid/${prop.item.id}`, {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${token}`}
        }).then (response => {if (!response.ok) throw new Error()} )
            .then(() => prop.getData())
            .catch(() => setErrorMessage("Failed to check item."));
    }



    const deleteitem = () => {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/deleteitem/${prop.item.name}`, {
                method: 'DELETE',
                headers: {
                    Authorization: `Bearer ${token}`}
                }).then (response => {if (!response.ok) throw new Error()} )
                .then(() => prop.getData())
                .catch(() => setErrorMessage("Failed to delete item."));
    }


    return (
        <div className={prop.item.status ? "todoitem1" : "todoitem2"} data-testid="the-item">
            <div className={errorMessage.length > 2 ? "error" : ""} data-testid="errorItem">
                {errorMessage}
            </div>
            <div className={prop.item.status ? "check1" : "check2"}>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemName')} {prop.item.name}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemDescription')} {prop.item.description}</div>
                <div className={prop.item.status ? "check1" : "check2"}> {t('itemDeadline')} {prop.item.formattedEndDate}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemID')} {prop.item.id}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemStatus')} {prop.item.status ? t('todoChecked') : t('todoOpen')}</div>
            </div>
            <div className={"buttonBack"}>
                <button className="button" data-testid="chekcbuttontest" onClick={checkitem}>{t('buttonCheckItem')}</button>
                <button className="button" data-testid="deletebuttontest" onClick={deleteitem}>{t('buttonDeleteItem')}</button>
                <Link to ={`${prop.item.id}`} className="link">{t('buttonEditItem')}</Link>
            </div>

        </div>
    )
}