import ToDoApp from "./ToDoApp";
import './App.css'
import { useTranslation } from "react-i18next";
function App() {
    const { t } = useTranslation();
    return (
        <div>
            <h2>{t('title')}</h2>
            <div>

            <ToDoApp/>
                </div>
        </div>
    );
}

export default App;
