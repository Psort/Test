import {useEffect, useState} from "react";
import axios, {InternalAxiosRequestConfig} from "axios";


export const authorizedApi = axios.create();

export function withAxiosIntercepted<T extends JSX.IntrinsicAttributes>(
    Component: React.ComponentType<T>
) {

    return function AxiosIntercepted(props: T) {
        const [isInitialized, setIsInitialized] = useState<boolean>(false);

        useEffect(() => {
            axios.interceptors.request.use((config: InternalAxiosRequestConfig) => {
                return {
                    ...config,
                    baseURL: process.env.REACT_APP_API_URL,
                };
            });

            authorizedApi.interceptors.request.use(async (config: InternalAxiosRequestConfig) => {

                return {
                    ...config,
                    baseURL: process.env.REACT_APP_API_URL,
                };
            });

            authorizedApi.interceptors.response.use(
                (response) => {
                    return response;
                },
                (error) => {
                    return Promise.reject(error);
                }
            );

            setIsInitialized(true);
        }, []);


        return isInitialized ? <Component {...props} /> : <></>;
    };
}
