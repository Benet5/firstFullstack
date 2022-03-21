import {FormEvent, useEffect, useState} from "react";
import {useTranslation} from "react-i18next";

import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "./AuthProvider";


export default function Register(){


    const [errorMessage, setErrorMessage] = useState('')
    const [userEmail, setUserEmail] =useState('')
    const [userPassword, setUserPassword] =useState('')
    const [userPasswordValidate, setUserPasswordValidate] =useState('')
    const { t } = useTranslation();
    const navigate = useNavigate();
    const {token} = useAuth();


    useEffect( () => {
        setErrorMessage('')
        if (token.length>2)
        {navigate("/todoapp")}
        }, [token, navigate]
    )



    const registerService = (event : FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        if (userEmail.length > 2 && userPassword === userPasswordValidate) {
            return fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/auth`, {
                method: 'POST',
                body: JSON.stringify({
                    email: userEmail,
                    password: userPassword,
                    passwordValidate: userPasswordValidate
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then (response => {if (!response.ok) throw new Error("Den Nutzer gibt es schon!")} )
                .then(() => navigate("/todoapp/auth/login"))
                .catch(e =>setErrorMessage(e.message))}
        else{
            setErrorMessage("Passwörter stimmen nicht überein.");
        }
    }


    return(

            <div>
                <div className={errorMessage.length > 2 || (userPassword !== userPasswordValidate)? "error" : ""} data-testid={"errorItemApp"}>
                    {errorMessage}
                </div>
                <div >
                    <form className="create" onSubmit={ev =>registerService(ev)}>
                        <h3>{t("register")}</h3>
                        <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderEmail")} value={userEmail} onChange={e => setUserEmail(e.target.value)}/></div>
                        <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderPassword")} value={userPassword} onChange={e => setUserPassword(e.target.value)}/></div>
                        <div><input className="input" type ={'text'} placeholder={t("inputPlaceholderPassword")} value={userPasswordValidate} onChange={e => setUserPasswordValidate(e.target.value)}/></div>
                        <button className="button" type='submit'>{t('register')}</button>
                    </form>
                </div>


            </div>




    )

}