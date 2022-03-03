import {ItemStructure} from "./model";
import { useTranslation } from "react-i18next";
//import {useState} from "react";
interface ToDoItemprops{
    item: ItemStructure
    getData: () => void;
}
export default function ToDoItem(prop: ToDoItemprops) {
    const { t } = useTranslation();
    const checkitem = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/checkitemid/${prop.item.id}`, {
            method: 'PUT',
        }).then(() => prop.getData())
    }



    const deleteitem = () => {
            fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/deleteitem/${prop.item.name}`, {
                method: 'DELETE'
                }).then(() => prop.getData())
    }


    return (
        <div className={prop.item.status ? "todoitem1" : "todoitem2"} data-testid="the-item">
            <div className={prop.item.status ? "check1" : "check2"}>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemName')} {prop.item.name}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemDescription')} {prop.item.description}</div>
                <div className={prop.item.status ? "check1" : "check2"}> {t('itemDeadline')} {prop.item.formattedEndDate}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemID')} {prop.item.id}</div>
                <div className={prop.item.status ? "check1" : "check2"}>{t('itemStatus')} {prop.item.status ? t('todoChecked') : t('todoOpen')}</div>
            </div>
            <div className={"buttonBack"}>
                <button className="button" onClick={checkitem}>{t('buttonCheckItem')}</button>
                <button className="button" onClick={deleteitem}>{t('buttonDeleteItem')}</button>
            </div>

        </div>
    )
}