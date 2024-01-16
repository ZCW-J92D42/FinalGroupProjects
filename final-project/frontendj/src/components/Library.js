import { useEffect, useState } from 'react';
import './Library.css';
import { useNavigate } from 'react-router-dom';
import CreateDocumentIcon from "./CreateDocumentIcon";
import Cookies from 'js-cookie';

function Document({ ...docDetails }) {
    // formatting date from api to Day Month, Year (i.e. 4 January, 2024)
    const date = new Date(docDetails.modificationDate)
    const formattedDate = date.toLocaleDateString("en-GB", {
        day: "numeric",
        month: "long",
        year: "numeric"
    })
    const [createClicked, setCreateClicked] = useState(false);

    // LOOK AT THIS LATER for passing doc id to the editor/view page
    // https://stackoverflow.com/questions/72004170/how-to-pass-id-in-route-react

    let navigate = useNavigate();
    const routeToDocumentView = () => {
        let path = `/document/` + docDetails.id;
        navigate(path);
    }

    // resource: https://stackoverflow.com/questions/2385113/howto-div-with-onclick-inside-another-div-with-onclick-javascript
    const confirmDeletion = (e) =>{
        // this is to stop react from doing the onClick event for the 
        // document (routeToDocView) & only do this onClick for the delete button
        e.stopPropagation(); 
        console.log("pressed the delete button")
        setCreateClicked(true);

    }

    // setting createClicked to false to make the overlay + 
    // delete confirmation menu go away. works when user hits no or 
    // out of the menu
    const handleButtonclick = () => {
        if(createClicked){
            setCreateClicked(false)
        }
    }

    // the delete request
    const handleSubmit = () => {
        const token = `Bearer ` + Cookies.get('token');
        // fetch request to our backend to get documents
        fetch('http://localhost:8080/document/delete/' + docDetails.id, {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                Authorization: token,
            },
        }).then((res) => {
            console.log("successfully deleted doc!")
            // refresh to update docs displayed
            window.location.reload();
            return res.json(); // returning the response as a json... idk what this does
        }).catch((e) => {
            console.log('Error: ' + e);
        });
        
        
    }


    return (
        <>
            <div className="card" onClick={() => routeToDocumentView()}>
                <div className="img">
                    {localStorage.getItem('userId') == docDetails.authorId && <div className="save" onClick={(event) => confirmDeletion(event)}>
                        <svg className="trashcan icon-trashcan ct-delete" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewBox="0 0 25 24.8">
                            <g className="trashcan-open">
                                <path d="M18.7,24.4H5.9L4.9,7h14.9L18.7,24.4z M7.6,22.6H17l0.8-13.7h-11L7.6,22.6z"></path>
                                <polygon points="13.6,10.3 13.1,21.2 14.9,21.2 15.4,10.3 "></polygon>
                                <polygon points="11.5,21.2 11,10.3 9.2,10.3 9.7,21.2 "></polygon>
                                <path d="M19.1,0.7l-4.7,0.9l-0.8-1.4L8.2,1.3L8,3l-4.7,1l0.2,4.7l17.3-3.5L19.1,0.7z 
             
             M8.8,1.9l4.4 -1.0 l0.5,0.8
             L8.7,2.8z 
             
             M5.2,6.4l0-1L18,2.8l0.3,0.9L5.2,6.4z"></path>
                            </g>
                            <g className="trashcan-closed">
                                <path d="M6.8,8.8h11L17,22.6
             H7.6L6.8,8.8z 
             M4.9,7l1,17.4h12.8
             l1-17.4
             H4.9z"></path>
                                <polygon points="13.6,10.3 13.1,21.2 14.9,21.2 15.4,10.3 "></polygon>
                                <polygon points="11.5,21.2 11,10.3 9.2,10.3 9.7,21.2 "></polygon>
                                <path d="M20.4,4h-4.8l-0.5-1.6
             H9.5L9,4
             H4.2
             L3.5,8.6h17.6
             L20.4,4z 
             
             M9.9,3.2h4.8
             L14.9,3.9h-5.2z
             
             M5.6,6.7l0.2-1 h13l0.2,1
             H5.6z"></path>
                            </g>
                        </svg>
                    </div>}
                </div>

                <div className="text">
                    <p className="h3"> {docDetails.title} </p>
                    <p className="p"> {docDetails.author} </p>
                    <p className="p"> Modified: {formattedDate} </p>
                </div>
            </div>

            {createClicked && 
            <>
            <div className="overlay" onClick={handleButtonclick}></div>
            <div className="delete-confirmation">
                    <h5>Are you sure you want to delete '{docDetails.title}'?</h5>
                    <span><button className='no-delete' onClick={handleButtonclick}>No</button><button className='yes-delete' onClick={handleSubmit}>Yes</button></span>    
            </div>
            </>}
        </>
    )
}


// resources: https://www.freecodecamp.org/news/how-to-fetch-api-data-in-react/
// {param} makes it so that we set props.param the value that we pass in
const Library = ({ urlPath, page }) => {
    const [documents, setDocuments] = useState([]);

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
    
      
    

    const [sortedDocuments, setSortedDocuments] = useState(documents);
    const sortByTitle = () => {
        const sortedByTitle= [...sortedDocuments].sort((a, b) => {
          //return new Date(b.modificationDate) - new Date(a.modificationDate);
        });

       
          // fetch request to our backend to get documents
          fetch('http://localhost:8080/document/getsorted', {
            method: 'GET',
        }).then((res) => {
            return res.json(); // returning the response as a json... idk what this does
        }).then((data) => { // get the data from the response
            //console.log(data);
            setDocuments(data); // save the data into our variable documents
        });
 
         setSortedDocuments(sortedByTitle);
      };
    



      const sortByDate = () => {
        const sortedByDate= [...sortedDocuments].sort((a, b) => {
          return a.title.localeCompare(b.title);
        });

        
            // fetch request to our backend to get documents
            fetch('http://localhost:8080/document/getAll', {
              method: 'GET',
          }).then((res) => {
              console.log("After fetch");
              console.log(res);
              return res.json(); // returning the response as a json... idk what this does
          }).then((data) => { // get the data from the response
              console.log(data);
              setDocuments(data); // save the data into our variable documents
          });
     

          setSortedDocuments(sortedByDate);
      };




    return (
        <>

            <p></p>
            <div>
      
            <button className="sort-date-button" onClick={sortByDate}>Sort by Date</button>

      <button className="sort-title-button" onClick={sortByTitle}>Sort by Title</button>



            <div className='Library'>
                {/* put create here */}
                <CreateDocumentIcon page={page} />
                {documents.map((doc) => { // for every doc in documents,
                    return <><Document {...doc} /></> // make a document component & pass in doc's data to it
                })}
             
      
     {/* {sortedDocuments.map((doc) => (
        <Doc key={doc.id} docDetails={doc} />
      ))} */
      }
    </div>
    

         </div></>
    )
};

export default Library

