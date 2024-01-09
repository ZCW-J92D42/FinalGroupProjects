import TextEditor from "../components/TextEditor";
import LoginButton from "../components/LoginButton";
import RegisterButton from "../components/RegisterButton";

function MainPage() {
    return (
        <div className="App">
            <header className="App-header">
                <TextEditor/>
                <LoginButton/>
                <RegisterButton/>
            </header>
        </div>

);
}

export default MainPage;
