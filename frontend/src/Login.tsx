import {FormEvent, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {Link, useNavigate} from "react-router-dom";
import {login} from "./AuthService";

export default function Login(){
    const[token, setToken] = useState(localStorage.getItem('jwt') ?? '')
    const [errorMessage, setErrorMessage] = useState('')
    const [userEmail, setUserEmail] =useState('')
    const [userPassword, setUserPassword] =useState('')
    const { t } = useTranslation();
    const navigate = useNavigate();

    useEffect( () => {
            if (token.length>2) {
                navigate("/todoapp")
            }
        }, [token, navigate]
    )


    useEffect( () => {
        setErrorMessage('')
        localStorage.setItem('jwt', token);
        }, [token]
    )

    const loginService = (event : FormEvent<HTMLFormElement>) => {
        event.preventDefault()
           login(userEmail, userPassword)
               .then((response : string) => setToken(response))
               .then(() => navigate("/todoapp"))
               .catch(e =>setErrorMessage(e.message))
    }



    return(
        <div>
            <div className={errorMessage.length > 2 ? "error" : ""} data-testid={"errorItemApp"}>
                {errorMessage}
            </div>
            <div >
                <form className="create" onSubmit={ev =>loginService(ev)}>
                    <h3>{t("login")}</h3>
                    <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderEmail")} value={userEmail} onChange={e => setUserEmail(e.target.value)}/></div>
                    <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderPassword")} value={userPassword} onChange={e => setUserPassword(e.target.value)}/></div>
                    <button className="button" type='submit'>{t('login')}</button>
                </form>
            </div>
            <div >
                <h3>{t("inoutRegisterCmd")} </h3>
                 <div className="flex"><Link to="/todoapp/auth" type='submit' className="buttonLogs">{t('buttonRegister')} </Link></div>
            </div>
        </div>



    )

}