
import './App.css'
import { useTranslation } from "react-i18next";
import { Outlet} from "react-router-dom";

function App() {
    const { t } = useTranslation();

    return (
        <div>
                <div className= "header">
                    <h2>{t('title')}</h2>
                </div>
            <div>



            <Outlet/>

            </div>

        </div>
    );
}

export default App;
