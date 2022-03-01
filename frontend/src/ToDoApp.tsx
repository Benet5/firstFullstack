import {useEffect, useState} from "react";
import {ItemStructure} from "./model";
import ToDoItem from "./ToDoItem";


export default function ToDoApp(){
    const [name, setName] =useState(''); // nach Name suchen lassen
    const [description, setDescription] = useState('');
    const [zeitraum, setZeitraum] = useState('');
    const [allData, setAllData] = useState([] as Array <ItemStructure>)



    useEffect( () => {
        getAllData()
    }, []
    )


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
        }
    }

    const deletechecked = () => {
            fetch("http://127.0.0.1:8080/todoapp/checkeditems", {
                method: 'PUT'
            }).then(response => response.json())
                .then((todosFromBackend: Array<ItemStructure>) => setAllData(todosFromBackend));
        }



    return(
        <div>
        <div>
            <div className="create">
            <h3>Item erstellen</h3>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Name'} value={name} onChange={e => setName(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Description'} value={description} onChange={e => setDescription(e.target.value)}/></div>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Zeitraum'} value={zeitraum} onChange={e => setZeitraum(e.target.value)}/></div>
                <button className="button" onClick={postData}>Item erstellen</button>
            </div>
            <div className="create">
            <h3>Item Suchen</h3>
            <div><input className="input" type ={'text'} placeholder={'To-Do-Name'} value={name} onChange={e => setName(e.target.value)} /></div>
           <div><input className="input" type ={'text'} placeholder={'To-Do-Zeitraum'} value={zeitraum} onChange={e => setZeitraum(e.target.value)}/></div>
            </div>
        </div>
            <div className="lowernavbar">

                <button className="button" onClick={getAllData}>Alle Items anzeigen</button>
                <button className="button" onClick={deletechecked}>Alle gecheckten Items löschen</button>

            </div>

        <div className="itemlist">
        {
            allData.length >0
                ?
                allData.filter(e => e.name.toLowerCase().includes(name.toLowerCase()))
                    .map(e => <ToDoItem item ={e} key={e.id} getData={getAllData}/>)
                :
                <div>Keine Ergebnisse oder Seite wird neu geladen</div>
        }
        </div>
        </div>

)
}