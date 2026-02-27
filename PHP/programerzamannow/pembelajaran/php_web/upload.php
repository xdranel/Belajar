<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $file_name = $_FILES["upload_file"]["name"];
    $file_type = $_FILES["upload_file"]["type"];
    $file_size = $_FILES["upload_file"]["size"];
    $file_tmp_name = $_FILES["upload_file"]["tmp_name"];
    $file_error = $_FILES["upload_file"]["error"];

    move_uploaded_file($file_tmp_name, __DIR__ . "/uploads/" . $file_name);
}
?>

<html lang="en">
<head>
    <title>Contoh Upload</title>
</head>
<body>
<h1>&_FILES</h1>

<?php if ($_SERVER["REQUEST_METHOD"] == "POST"){ ?>
        <h1>File Uploaded</h1>
    <table border="2">
        <tr>
            <td>File Name</td>
            <td>File Type</td>
            <td>File Size</td>
            <td>File Tmp Name</td>
            <td>File Error</td>
        </tr>
        <tr>
            <td><?= $file_name ?></td>
            <td><?= $file_type ?></td>
            <td><?= $file_size ?></td>
            <td><?= $file_tmp_name ?></td>
            <td><?= $file_error ?></td>
        </tr>
    </table>
<?php } ?>

<h1>Form Upload</h1>
<form action="upload.php" method="post" enctype="multipart/form-data">
    <label>Upload File :
        <input type="file" name="upload_file">
    </label>
    <input type="submit" value="Upload" name="submit">
</form>
</body>
</html>
