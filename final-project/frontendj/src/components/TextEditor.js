import React, { useState, useEffect } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css'; // Import the styles
import Cookies from 'js-cookie';

const TextEditor = (id) => {
    // read article to maybe parse string from quill
    // https://davidj-fertitta.medium.com/an-ultra-quick-guide-to-quill-js-1aae1ac59d56
    const [content, setContent] = useState('');
    const [document, setDocument] = useState('');
    let userId = localStorage.getItem('userId');
    const [canEdit, setCanEdit] = useState(false);
    const [title, setTitle] = useState(''); 
    const [docId, setDocId] = useState('');
    const [authorId, setAuthorId] = useState('');

    // fetching document from backend
    useEffect(() => {
        // using the id that was passed to TextEditor by the EditorPage to get the right document's info
        fetch('http://localhost:8080/document/view/'+id.id, {
            method: 'GET',
        }).then((res) => {
            return res.json();
        }).then((data) => {
            setContent(data.body);
            setDocument(data);
            setCanEdit(userId == data.authorId);
            setTitle(data.title);
            setDocId(data.id);
            setAuthorId(data.authorId);

        });
    }, []); // REMEMBER THIS ENDING PART, OR ELSE IT'LL FETCH FOREVER

    const handleChange = (value) => {
        console.log(value);
        setContent(value);
        //console.log(value);
    };

    // resources: maybe will need this later when saving updates
    // https://jasonwatmore.com/post/2020/02/01/react-fetch-http-post-request-examples
    const handleSave = () => {
        // Your save logic here
        console.log(content)
        const token = `Bearer ` + Cookies.get('token');
        const requestJSON = {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
                Authorization: token,
            },
            body: JSON.stringify({ id: docId, title: title, body: content, authorId: authorId })
        }
        console.log(requestJSON);
        console.log(document);
        fetch('http://localhost:8080/document/update', requestJSON).then((res) => {
            console.log(res);
        }).then((data) => {
            setDocument(data);
        }).catch((e) => {
            console.log(e);
        })
        
    };

    // Define a custom toolbar
    const modules = {
        toolbar: [
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            ['bold', 'italic', 'underline', 'strike'],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
            ['link', 'image', 'video'],
            ['clean'],
        ],
    };

    // Define the formats
    const formats = [
        'header',
        'bold',
        'italic',
        'underline',
        'strike',
        'list',
        'bullet',
        'link',
        'image',
        'video',
    ];

    // Set default text color to black
    const styles = {
        color: 'black',
    };

    return (
        <div class="editor" style={{
            position: 'absolute', left: '50%', top: '25%',
            transform: 'translate(-50%, -15%)'
        }}>
            <h1>Text Editor</h1>
            <h2>{title}</h2>
            <button id="save-btn" onClick={handleSave}>Save</button>
            <ReactQuill
             readOnly = {!canEdit}
                value={content}
                onChange={handleChange}
                modules={modules}
                formats={formats}
                style={{ height: '900px', width: '1000px', ...styles }}
            />
            <div>
                {/* <p>Content:</p>
                <div dangerouslySetInnerHTML={{ __html: content }} /> */}
            </div>
            
            {/* <button onClick={() => setReadOnly(!readOnly)}>
                {readOnly ? 'Enable Editing' : 'Disable Editing'}
            </button> */}
        </div>
    );
};

export default TextEditor;
