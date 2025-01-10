import React,{useState} from "react";
import axios from "axios";

function ShortUrlForm() {
    //WE want to create three variable that will be updating through the form
    const [originalUrl, setOriginalUrl] = useState('');
    const [shortUrl, setShortUrl] = useState('');
    const [error, setError] = useState('');

    //Now we want to get the original url and send it to our backend.
    //hoping our backend is able to respond with a short url
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setShortUrl('');
    
    try {
        const response = axios.post('http://localhost:8080/api/url/shorten', {originalUrl});
        setShortUrl(`http://localhost:8080/${(await response).data.shortUrl}`);
        console.log(response.data);
    } catch (error) {
        console.error('Axios Error:', error);
        setError('Failed to shorten the url');
    }
    };
return(
    <div style={{textAlign: 'center', marginTop: '50px'}}>
        <h1>URL Shortner</h1>
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Enter Original URL"
                value={originalUrl}
                onChange={(e) => setOriginalUrl(e.target.value)}
                required
                style={{padding: '10px', width: '300px', marginBottom: '10px'}}
            />
            <br/>
            <button type="submit" style={{padding: '10px 20px'}}>
                Shorten URL
            </button>
        </form>

        {shortUrl && (
            <div style={{marginTop: '20px'}}>
                <h2>Shortened URL: </h2>
                <a href={shortUrl} target="_blank" rel="noopener noreferrer">
                    {shortUrl}
                </a>
            </div>
        )}
        {error && <p style={{color:'red'}}>{error}</p>}
    </div>
    )
}

export default ShortUrlForm;