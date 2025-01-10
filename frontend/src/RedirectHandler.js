import React, {useEffect} from "react";
import {useParams} from 'react-router-dom';
import axios from "axios";

function RedirectHandler(){
    const {shortUrl} = useParams();

    useEffect(()=> {
        const fetchOriginalUrl = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/${shortUrl}`);
                console((response).data);
                if (response.status === 302) {
                    const {originalUrl} = response.data;
                    window.location.href = originalUrl;
                }
            } catch (error) {
                console.error('Error redirecting', error);
                alert('Failed to redirect. Short Url not found!');
            }
        };
        fetchOriginalUrl();
    }, [shortUrl]);
    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
          <h1>Redirecting...</h1>
        </div>
      );
}
export default RedirectHandler;