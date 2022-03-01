import {ItemStructure} from "./model";
//import {useState} from "react";
interface ToDoItemprops{
    item: ItemStructure
    getData: () => void;
}
export default function ToDoItem(prop: ToDoItemprops) {

    const checkitem = () => {
        fetch(`http://127.0.0.1:8080/todoapp/checkitemid/${prop.item.id}`, {
            method: 'PUT',
        }).then(() => prop.getData())
    }


    const checkantwort = (status: boolean) => {
        if (!status) {
            return 'To-Do ist noch offen!';
        } else {
            return "To-Do ist schon gecheckt!";
        }
    }


    const deleteitem = () => {
            fetch("http://127.0.0.1:8080/todoapp/deleteitem/" + prop.item.name, {
                method: 'DELETE'
                }).then(() => prop.getData())
    }


    return (
        <div className={prop.item.status ? "todoitem1" : "todoitem2"}>
            <div className={prop.item.status ? "check1" : "check2"}>
                <div className={prop.item.status ? "check1" : "check2"}>Name: {prop.item.name}</div>
                <div className={prop.item.status ? "check1" : "check2"}>Beschreibung: {prop.item.description}</div>
                <div className={prop.item.status ? "check1" : "check2"}>Deine Deadline: {prop.item.formattedEndDate}</div>
                <div className={prop.item.status ? "check1" : "check2"}>ID: {prop.item.id}</div>
                <div className={prop.item.status ? "check1" : "check2"}>Status: {checkantwort(prop.item.status)}</div>
            </div>
            <div className={"buttonBack"}>
                <button className="button" onClick={checkitem}>Item checken</button>
                <button className="button" onClick={deleteitem}>Item löschen</button>
            </div>

        </div>
    )
}