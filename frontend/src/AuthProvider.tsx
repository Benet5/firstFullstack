import {ReactNode, useContext, useEffect, useState} from "react";
import AuthContext from "./AuthContext";
import {useNavigate} from "react-router-dom";

export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}:{children : ReactNode}){

    const [token, setToken] = useState('');
    const navigate = useNavigate();


    const login = (userEmail : string, userPassword : string) => {
        if (userEmail.length > 2) {
            return fetch(`${process.env.REACT_APP_BASE_URL}/todoapp/auth/login`, {
                method: 'POST',
                body: JSON.stringify({
                    email: userEmail,
                    password: userPassword,
                }),
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(response => {
                if (response.ok) {
                    return response.text()
                        .then((response : string) => setToken(response))
                }
                throw new Error("Logindaten nicht gültig.")
            })
        } else {
            return Promise.reject("Eingabe zu kurz!");
        }

    }


    const logout = () => {
setToken('');


    }



    return <AuthContext.Provider value={{token, login, logout}}> {children}</AuthContext.Provider>;
}

