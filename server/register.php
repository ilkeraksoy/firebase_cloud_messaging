<?php

	if(isset($_POST['Token'])) {

		$token = $_POST['Token'];
		
		$conn = mysqli_connect("localhost", "root", "", "fcm");

		$query = "INSERT INTO users(token) VALUES('$token') ON DUPLICATE KEY UPDATE token='$token';";
		
		mysqli_query($conn, $query);
		mysqli_close($conn);
	}
?>