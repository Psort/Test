import React, { useState, useEffect, useCallback } from 'react';
import { Button, Section } from "../App.styles";
import { UserResponse } from "../model/api/UserResponse";
import { UserApi } from "../api/UserApi";
import { USER } from "../constants/constants";
import ProductList from "../components/ProductList";

export const HomePage = () => {
    const [user, setUser] = useState<UserResponse | null>(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isLoginFormVisible, setIsLoginFormVisible] = useState(false);
    const [isRegisterMode, setIsRegisterMode] = useState(false);

    useEffect(() => {
        const storedUser = localStorage.getItem(USER);
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
    }, []);

    const handleAuthentication = useCallback(async (isRegister: boolean) => {
        const authAction = isRegister ? UserApi.create : UserApi.login;
        try {
            const response = await authAction({ login: username, password });
            console.log(response);
            localStorage.setItem(USER, JSON.stringify(response.data));
            setUser(response.data);
        } catch (error: any) {
            console.error(error);
        } finally {
            setIsLoginFormVisible(false);
        }
    }, [username, password]);

    const handleLogout = () => {
        localStorage.removeItem(USER);
        setUser(null);
    };

    return (
        <Section>
            {user ? (
                <div>
                    Logged in as {user.login} : balance : {user.balance}
                    <Button onClick={handleLogout}>Logout</Button>
                </div>
            ) : (
                <Button onClick={() => setIsLoginFormVisible(true)}>
                    {isRegisterMode ? "Register" : "Login"}
                </Button>
            )}

            {user && <ProductList />}

            {isLoginFormVisible && (
                <div className="login-form">
                    <h2>{isRegisterMode ? "Register" : "Login"}</h2>
                    <input
                        type="text"
                        placeholder="Enter username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <input
                        type="password"
                        placeholder="Enter password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button onClick={() => handleAuthentication(isRegisterMode)}>
                        {isRegisterMode ? "Register" : "Login"}
                    </Button>
                    <p>
                        {isRegisterMode ? (
                            <span onClick={() => setIsRegisterMode(false)}>Already have an account? Login here</span>
                        ) : (
                            <span onClick={() => setIsRegisterMode(true)}>Don't have an account? Create one here</span>
                        )}
                    </p>
                </div>
            )}
        </Section>
    );
};

export default HomePage;
