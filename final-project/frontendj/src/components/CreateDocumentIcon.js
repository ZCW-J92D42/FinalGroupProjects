
import add from '../add.png';
import './CreateDocumentIcon.css';
import {Link} from "react-router-dom";
import {useState} from "react";
import Cookies from 'js-cookie';

const CreateDocumentIcon = ({page}) => {

    const [isFormOpen, setFormOpen] = useState(false);
    const [title, setDocumentTitle] = useState('');
    const [body, setDocumentBody] = useState('');

    const isLoggedIn = () => {
        return localStorage.getItem('userInfo') !== null;
    }


    const refreshPage = () => {
        window.location.reload(); // Maybe used later
    }





    const handleClick = async () => {
        if (isLoggedIn()){
            setFormOpen(true);

        } else {
            console.log("You cannot create!")
        }
    };

    const openForm = () => {
        setFormOpen(true);
    }
    const closeForm = () => {
        setFormOpen(false);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();


        try {
            console.log("IM HERE FIRST");


            const document = {
            "title": title, "body": body
            }

            console.log("IM HERE");
            const response = await fetch('http://localhost:8080/document/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: 'Bearer '+Cookies.get('token'),
                },
                body: JSON.stringify(document)

            });


        refreshPage();


        } catch (error) {
            console.log('Cannot create document:', error.message);
        }
        closeForm();
    };

    if(localStorage.getItem('userId') != null && page === "Main"){
        console.log("in if")
        return (
            <div>
                {isLoggedIn() ? (
                    <img className="image"
                        src={add}
                        alt="Create Document"
                        onClick={handleClick}
                        style={{cursor:'pointer'}}
                    />

                    ) : (

                            <img
                                src={add}
                                alt="Create Document"
                                className="image"
                            />

                    )}

                {isFormOpen && (
                    <div className="form-popup" id="myForm">
                        <form onSubmit={handleSubmit} className="form-container">
                            <h1>Create Document</h1>

                            <input
                                type="text"
                                placeholder="Enter Document Title"
                                name="docTitle"
                                value={title}
                                onChange={(e) => setDocumentTitle(e.target.value)}
                            />
                            <p>

                            <button type="submit" className="btn">Create</button>
                            <button type="button" className="btncancel" onClick={closeForm}>Close</button>
                            </p>
                        </form>
                    </div>
                )}
            </div>

        );
     }
};

export default CreateDocumentIcon;