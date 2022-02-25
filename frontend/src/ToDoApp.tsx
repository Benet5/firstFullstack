import {useEffect, useState} from "react";
import {ItemStructure} from "./model";
import ToDoItem from "./ToDoItem";

export default function ToDoApp(){
    const [name, setName] =useState(''); // nach Name suchen lassen
    const [description, setDescription] = useState('');
    const [zeitraum, setZeitraum] = useState('');
    const [allData, setAllData] = useState([] as Array <ItemStructure>)
    const [errormessage, setErrormessage] = useState('')

    useEffect( () => {
        getAllData()
    }, []
    )

    const checkitem = () => {
        if (name.length > 2) {
            fetch("http://127.0.0.1:8080/todoapp/checkitem/" + name, {
                method: 'PUT',
                body: JSON.stringify({
                    name: name,
                    description: description,
                    zeitraum: zeitraum
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(() => getAllData())
        } else setErrormessage('Da lief was schief!')
    }


  const getAllData = () => {
        fetch("http://127.0.0.1:8080/todoapp/getallitems")
            .then(response => response.json())
            .then((response2 : Array<ItemStructure>) => {setAllData(response2)})

    }

    const postData = () => {
        if (name.length > 2) {
            fetch("http://127.0.0.1:8080/todoapp", {
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
        } else setErrormessage('Da lief was schief!')
    }


    const deleteitem = () => {
        if (name.length > 2) {
            fetch("http://127.0.0.1:8080/todoapp/deleteitem/" + name, {
                method: 'DELETE',
                body: JSON.stringify({
                    name: name,
                    description: description,
                    zeitraum: zeitraum
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(() => getAllData())
        } else setErrormessage('Da lief was schief!')
    }



    // hier die checkitemfunktion mit übergeben, um sie via button in todoItem aufzureufen
    const items =
        allData.map(e => <ToDoItem formattedEndDate={e.formattedEndDate} name={e.name} description={e.description} id={e.id} status={e.status}/>)



    return(
        <div>
        <div className="navbar">
            <div><input className="input" type ={'text'} placeholder={'To-Do-Name'} value={name} onChange={e => setName(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Description'} value={description} onChange={e => setDescription(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Zeitraum'} value={zeitraum} onChange={e => setZeitraum(e.target.value)}/></div>

        </div>
            <div className="lowernavbar">
                <button className="button" onClick={postData}>Item erstellen</button>
                <button className="button" onClick={checkitem}>Item abhaken</button>
                <button className="button" onClick={getAllData}>Alle Items anzeigen</button>
                <button className="button" onClick={deleteitem}>Item löschen</button>
            </div>

        <div className="itemlist">
        {
            items.length >0
                ?
                items
                :
                <div>Keine Ergebnisse oder Seite wird neu geladen</div>
        }
        </div>
        </div>

)
}