import {Link} from "react-router-dom";
import {t} from "i18next";
import {useEffect} from "react";
import {logout} from "./AuthService";

export default function Logout(){
    useEffect(() => {logout()})



    return(
        <div>
            <div className="logout"><h3>{t('logoutMessage')}</h3></div>

            <div className="flex" > <Link to="todoapp/auth/login" className="buttonLogs">{t('buttonLogin')}</Link> </div>

        </div>
    )


}