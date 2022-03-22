
import './App.css'
import { useTranslation } from "react-i18next";
import {Link, Outlet} from "react-router-dom";
import {useAuth} from "./AuthProvider";

function App() {
    const { t } = useTranslation();
    const {token} = useAuth();
    return (
        <div>
                <div className= "header">
                    <h2>{t('title')}</h2>
                </div>
            <div>
                {!token &&
                    <div>
                    <div className="flex"> <h3>{t("inoutRegisterCmd")} </h3> <Link to="/todoapp/auth/login" className="buttonLogs"><h3>{t('login')}</h3></Link></div>
                    <div className="flex"> <h3>{t("inoutLoginCmd")} </h3><Link to="/todoapp/auth" className="buttonLogs"><h3>{t('register')}</h3></Link></div>
                    </div>
                }

            <Outlet/>

            </div>

        </div>
    );
}

export default App;
