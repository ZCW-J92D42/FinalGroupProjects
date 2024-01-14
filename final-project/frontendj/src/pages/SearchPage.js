import { useParams } from "react-router-dom";
import CreateDocumentIcon from "../components/CreateDocumentIcon";
import Doc from "../components/Doc";
import Library from "../components/Library";
import SearchBar from "../components/Searchbar";


function SearchPage() { 
  const filter = useParams();
    // const { search } = useParams(); // Pulling the data out of the URL
    // const results = JSON.parse(decodeURI(search));
    // console.log(search, results); // Fixed variable name
  console.log(filter);
    return (
      <div className="App">
        <br/>
        <br/>
        <SearchBar value={filter.search} placeholder="Enter a Keyword"/>
        <br/>
        <br/>
        <br/>
        <h1>Search Results for: '{filter.search}'</h1>
  
        {/* {results && results.length > 0 ? (
          // Use parentheses to wrap the map function
          results.map((doc, index) => (
            <Doc key={index} {...doc} /> // Render the Doc component with spread operator
          ))
        ) : (
          <div className="App">
            <h1>No Result</h1>
          </div>
        )} */}
        <Library urlPath={"search?filter=" + filter.search}></Library>
      </div>
    );
  }
  
  export default SearchPage;
  

