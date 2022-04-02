<h1>DriveDashVideoAPI</h1>
<p>  
A small demo application made using Ktor framework which is built using Kotlin and Coroutines from ground up.<br>This project is a very simple implementation of Ktor server deploying a RESTful API.<br>
</p>

# Usages

```http
GET /streams/?fileId=abcdefgh
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `fileId` | `string` | **Required**. Google drive file id |
| `accessToken` | `string` | *Optional*.  OAuth 2.0 token, authorized with `drive.file` scope |

## Response

API returns the JSON data with response code of `200 OK` or `403 Forbidden` if file requires authorization or provided Access token is expired/invalid.

```javascript
{
  "error"        : string,
  "streams"      : list[stream]
}
```
```javascript

"stream" : { 
  "resolution"   : string,
  "url"          : string,
  "quality"      : string,
  "driveStream"  : string    
}
```

The `error` attribute is nullable and is only attached if request threw a error along with a empty list of `streams`.

The `streams` attribute is contains list of streams which have following attribute:

- `resolution`: Dimension of the video
- `url`: Url of video for streaming
- `quality`: This field is generated from `resolution` mapping it to appropriate quality such as 1080p, 720p etc 
- `driveStream`: This contains the `cookie` required to access the `url` (Usage: `DRIVE_STREAM=$cookie`)
