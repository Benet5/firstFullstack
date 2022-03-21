
import './App.css'
import { useTranslation } from "react-i18next";
import {Link, Outlet} from "react-router-dom";

function App() {
    const { t } = useTranslation();

    return (
        <div>
                <div className= "header">
                    <h2>{t('title')}</h2>
                </div>
            <div>
                <div className="flex">
            <div> <Link to="/todoapp/auth/login" className=""><h3>{t('login')}</h3></Link></div>
            <div> <Link to="/todoapp/auth" className=""><h3>{t('register')}</h3></Link></div>
                </div>
            <Outlet/>

            </div>

        </div>
    );
}

export default App;
