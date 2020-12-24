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
        
        // Request made to the backend api 
        // Send formData object 
        axios.post("https://localhost:8090/api/Product/Upload/", 
                   formData,); 
    }

    const onFileDownload = () => {
        axios({
            url: "https://localhost:8090/api/Product/Download/food_master.csv",
            method: 'GET',
            responseType: 'blob',
        })
        .then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'food_master.txt'); //or any other extension
            document.body.appendChild(link);
            link.click();
         });; 
    }
    
    return (
        <React.Fragment>
            <div style={{"marginBottom":"100px"}}>
                <h3>Food master data download</h3>
                <button onClick={onFileDownload}> 
                    Download! 
                </button>
            </div>
            
            <div>
                <h3>Food master data upload</h3>
                <input type="file" id="file" name="file" accept=".txt, .csv" onChange={(e) => onFileChange(e)} />
                <button onClick={onFileUpload}> 
                    Upload! 
                </button>
            </div>
        </React.Fragment>
    );
}

export default Admin;