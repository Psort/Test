import React from "react";
import {AppContainer} from "./App.styles";
import 'react-toastify/dist/ReactToastify.css';
import {AppRouter} from "./router/App.router";
import {withAxiosIntercepted} from "./hooks/withAxiosIntercepted";
import {createTheme, ThemeProvider} from "@mui/material";

const theme = createTheme({
    palette: {
        mode: 'dark',
        background: {
            default: '#100D1A',
        },
        primary: {
            main: '#EE26E5',
        },
        secondary: {
            main: '#221D26',
        },
        text: {
            primary: '#fff',
        },
    },
});

function App() {
    return (
        <ThemeProvider theme={theme}>
            <AppContainer>
                <AppRouter/>
            </AppContainer>
        </ThemeProvider>
    );
}

export default withAxiosIntercepted(App);
