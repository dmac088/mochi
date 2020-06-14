import React, {useState} from 'react';
import { instance as axios } from "../../Helpers/api/axios";

function Admin() {

    const [stateObject, setObjectState] = useState({
        selectedFile: null,
    });

    const onFileChange = (e) => {
        setObjectState({
            selectedFile: e.target.files[0],
        });
    }

    const onFileUpload = () => {
        // Create an object of formData 
        const formData = new FormData(); 
     
         // Update the formData object 
        formData.append( 
          "file", 
          stateObject.selectedFile, 
          stateObject.selectedFile.name 
        ); 
     
        // Details of the uploaded file 
        console.log(stateObject.selectedFile); 
        
        // Request made to the backend api 
        // Send formData object 
        axios.post("https://localhost:8090/api/Product/Upload/", 
                   formData,); 
    }
    
    return (
        <React.Fragment>
            <h3>Food master data upload</h3>
            <input type="file" id="file" name="file" accept=".txt, .csv" onChange={(e) => onFileChange(e)} />
            <button onClick={onFileUpload}> 
                  Upload! 
            </button>
        </React.Fragment>
    );
}

export default Admin;