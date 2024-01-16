
import Library from "../components/Library";
import './MainPage.css';
import Footer from "../components/Footer";
import SearchBar from "../components/Searchbar";



function MainPage() {
    const path = `getAll`;
    const page = "Main";

    return (
        <>
            <div className="App" id="library">


                <SearchBar placeholder="Enter a Keyword" />

                <Library urlPath={path} page={page} />



                <footer>
                    <Footer></Footer>

                </footer>
            </div>
        </>
    );
}

export default MainPage;
