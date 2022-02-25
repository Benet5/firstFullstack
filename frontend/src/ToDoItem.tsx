import {ItemStructure} from "./model";
import {useState} from "react";

export default function ToDoItem(prop: ItemStructure) {

  // const [container, setContainer] = useState('#8C2D4B');

    const checkantwort = (status: boolean) => {
        if (!status) {
           // setContainer('#8C2D4B')
            return 'To-Do ist noch offen!';
        } else {
         //  setContainer('#64C8B9')
            return "To-Do ist schon abgehakt!";
        }
    }



    return (
        <div className="todoitem" style={{background: '#8C2D4B'}}>
            <div>
                <div>Name: {prop.name}</div>
                <div>Beschreibung: {prop.description}</div>
                <div>Deine Deadline: {prop.formattedEndDate}</div>
                <div>ID: {prop.id}</div>
                <div>Schon abgehakt? {checkantwort(prop.status)}</div>
            </div>
            <div>

            </div>

        </div>
    )
}