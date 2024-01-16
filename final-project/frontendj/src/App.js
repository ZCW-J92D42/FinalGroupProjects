import googledoc from './googledoc.svg'
import './App.css';
import Login from "./components/Login";
import Library from './components/Library'
import RoutePath from "./components/RoutePath";
import UserComponent from "./components/UserComponent";
import Register from "./components/Register";
import TextEditor from './components/TextEditor';
import LoginPage from './pages/LoginPage';
import { useNavigate } from 'react-router-dom';
import NaviBar from "./components/NaviBar";
import Searchbar from "./components/Searchbar";
import Footer from './components/Footer';



function App() {
    let navigate = useNavigate();
    const routeToMainPage = () => {
        console.log("clicked on image")
        let path = `/`;
        navigate(path);
    }



    return (


        <div className="App">
            <header className="App-header">
                <img src={googledoc} className="App-logo" alt="logo" onClick={routeToMainPage} />
                <a
                    className="App-link"
                    href="https://github.com/ZCW9-2-Peponapis/FinalGroupProjects"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    ZipDocs
                </a>

            </header>

       
            <NaviBar />
            <UserComponent />
            <RoutePath />
            
        
        </div>






    );



}

export default App;

