import {Route, Routes} from "react-router-dom";
import {HomePage} from "../page/HomePage";
import React from "react";

export const AppRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage/>}></Route>
        </Routes>
    )
}
