import "./Searchbar.css";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function SearchBar({ placeholder, dataResult }) {

    let [docs, setDocs] = useState([]) // sharing state whithin a component
    let [param, setParam] = useState("") // start at a empty string
    useEffect(() => {
        // fetch request to our backend to get documents
        fetch('http://localhost:8080/document/getAll', {
            method: 'GET',
        }).then((res) => {
            return res.json(); // returning the response as a json... idk what this does
        }).then((data) => { // get the data from the response
            //console.log(data);
            setDocs(data); // save the data into our variable documents
            console.log(data)
        });
    }, []);


    const inputChange = (event) => {
        setParam(event.target.value)
    }
    const navigate = useNavigate()

    const handleButtonclick = () => {
        let result = docs.filter(doc => doc.body.toLowerCase().includes(param.toLowerCase()) || doc.title.toLowerCase().includes(param.toLowerCase())) // filter through array
        const resultString = encodeURI(JSON.stringify(result))
        console.log(`filter result= `)
        console.log(result)
        console.log(resultString)
        console.log(param)
        navigate(`/search/${param}`)
    }

    return (
        <div className="parent-container">
            <div className="search">
                <div className="searchInputs">
                    <input type="text"
                        placeholder={placeholder}
                        onChange={inputChange}
                    />
                    <div className="searchIcon">
                        <button className="button-styling" onClick={handleButtonclick}>
                            Search
                        </button>
                    </div>

                </div>
                <div className="dataResult"></div>
            </div>
        </div>
    );
}



export default SearchBar