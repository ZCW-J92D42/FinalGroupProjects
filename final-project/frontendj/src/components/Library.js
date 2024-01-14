import { useEffect, useState } from 'react';
import './Library.css';
import { useNavigate } from 'react-router-dom';
import CreateDocumentIcon from "./CreateDocumentIcon";
import SearchBar from './Searchbar';

function Document({ ...docDetails }) {
    // formatting date from api to Day Month, Year (i.e. 4 January, 2024)
    const date = new Date(docDetails.modificationDate)
    const formattedDate = date.toLocaleDateString("en-GB", {
        day: "numeric",
        month: "long",
        year: "numeric"
    })

    // LOOK AT THIS LATER for passing doc id to the editor/view page
    // https://stackoverflow.com/questions/72004170/how-to-pass-id-in-route-react

        let navigate = useNavigate();
        const routeToDocumentView = () => {
            let path = `/document/` + docDetails.id;
            navigate(path);
        }


    return (
        <>
        {/* TBA: make the entire thing a button, route to view document page
        something something <button onClick()={routeToDocumentView}>
        */}
            <div className="card" onClick={() => routeToDocumentView()}>
                <div className="img">
                    <div className="save">
                        <svg className="svg" width="683" height="683" viewBox="0 0 683 683" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <g clipPath="url(#clip0_993_25)">
                                <mask id="mask0_993_25" maskUnits="userSpaceOnUse" x="0" y="0" width="683" height="683">
                                    <path d="M0 -0.00012207H682.667V682.667H0V-0.00012207Z" fill="white"></path>
                                </mask>
                                <g mask="url(#mask0_993_25)">
                                    <path d="M148.535 19.9999C137.179 19.9999 126.256 24.5092 118.223 32.5532C110.188 40.5866 105.689 51.4799 105.689 62.8439V633.382C105.689 649.556 118.757 662.667 134.931 662.667H135.039C143.715 662.667 151.961 659.218 158.067 653.09C186.451 624.728 270.212 540.966 304.809 506.434C314.449 496.741 327.623 491.289 341.335 491.289C355.045 491.289 368.22 496.741 377.859 506.434C412.563 541.074 496.752 625.242 524.816 653.348C530.813 659.314 538.845 662.667 547.308 662.667C563.697 662.667 576.979 649.395 576.979 633.019V62.8439C576.979 51.4799 572.48 40.5866 564.447 32.5532C556.412 24.5092 545.489 19.9999 534.133 19.9999H148.535Z" stroke="#CED8DE" strokeWidth="40" strokeMiterlimit="10" strokeLinecap="round" strokeLinejoin="round"></path>
                                </g>
                            </g>
                            <defs>
                                <clipPath id="clip0_993_25">
                                    <rect width="682.667" height="682.667" fill="white"></rect>
                                </clipPath>
                            </defs>
                        </svg>
                    </div>
                </div>

                <div className="text">
                    <p className="h3"> {docDetails.title} </p>
                    <p className="p"> {docDetails.author} </p>
                    <p className="p"> Modified: {formattedDate} </p>
                </div>
            </div>
        </>
    )
}


// resources: https://www.freecodecamp.org/news/how-to-fetch-api-data-in-react/
// {param} makes it so that we set props.param the value that we pass in
const Library = ({urlPath, page}) => {
    const [documents, setDocuments] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // fetch request to our backend to get documents
        fetch('http://localhost:8080/document/' + urlPath, {
            method: 'GET',
        }).then((res) => {
            return res.json(); // returning the response as a json... idk what this does
        }).then((data) => { // get the data from the response
            //console.log(data);
            setDocuments(data); // save the data into our variable documents
        });
    }, []);


    return (
        <>
        {/* library comment was here */}
        <p></p>

        <div className='Library'>
            {/* put create here */}
            <CreateDocumentIcon page={page}/>
            {documents.map((doc) => { // for every doc in documents,
                return <><Document {...doc} /></> // make a document component & pass in doc's data to it
            })}
        </div>
        </>
    )
};

export default Library

