import ToDoApp from "./ToDoApp";
import './App.css'
import { useTranslation } from "react-i18next";
import { Link, Outlet} from "react-router-dom";
function App() {
    const { t } = useTranslation();
    return (
        <div>
                <div className= "header">
                    <h2>{t('title')}</h2>
                </div>
            <div>
                <Link to="todoapp" className=""> <h3>Alle ToDos!</h3></Link>
            <Outlet/>
            </div>

        </div>
    );
}

export default App;
