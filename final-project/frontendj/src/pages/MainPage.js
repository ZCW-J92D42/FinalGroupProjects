
import CreateDocumentIcon from "../components/CreateDocumentIcon";
import { Outlet } from "react-router-dom";
import Library from "../components/Library";


function MainPage() {
    return (
        <>
        <Outlet/>
        <div className="App">
            <header className="App-header">
                Welcome to Zip Docs


                    <Library/>


            </header>
        </div>
        </> 
    );
}

export default MainPage;
