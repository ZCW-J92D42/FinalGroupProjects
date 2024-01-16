import { Outlet, useParams } from "react-router-dom";
import TextEditor from "../components/TextEditor";

function EditorPage() {
    const id = useParams(); // getting the id from the url
    return (
        <>
        {/* Apparently, Outlet is a thing that can maybe make it work */}
        <Outlet/>
        <div className="App">
            <header className="editorPage">
            <TextEditor id={id.id}/> {/* passing id to TextEditor */}
            </header>
        </div>
        </>
    );
}

export default EditorPage;
