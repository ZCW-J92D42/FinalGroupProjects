
import CreateDocumentIcon from "../components/CreateDocumentIcon";
import { Outlet } from "react-router-dom";
import Library from "../components/Library";


function MainPage() {
    const path = `getAll`;

    return (
        <>
        <Outlet/>
        <div className="App">
            <header className="App-header">
                Welcome to Zip Docs


                    <Library urlPath={path}/>


            </header>
        </div>
        </> 
    );
}

export default MainPage;
