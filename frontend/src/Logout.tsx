import {Link} from "react-router-dom";
import {t} from "i18next";


export default function Logout(){




    return(
        <div>
            <div className="logout"><h3>{t('logoutMessage')}</h3></div>

            <div className="flex" > <Link to="todoapp/auth/login" className="buttonLogs">{t('buttonLogin')}</Link> </div>

        </div>
    )


}