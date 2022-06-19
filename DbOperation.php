<?php
 
class DbOperation
{
    private $con;
 
    function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';
        $db = new DbConnect();
        $this->con = $db->connect();
    }
 
 //Добавление записи в базу данных
 //public function createArtist($name, $genre){
 //$stmt = $this->con->prepare("INSERT INTO artists (name, genre) VALUES (?, ?)");
 //$stmt->bind_param("ss", $name, $genre);
 //if($stmt->execute())
 //return true; 
 //return false; 
 //}
 
  //Удалить запись из базы данных
 //public function delArtist($id){
 //$stmt = $this->con->prepare("DELETE FROM artists WHERE id='$id'");
 //$stmt->bind_param("ss", $id);
 //if($stmt->execute())
 //return true; 
 //return false; 
 //}
 
   // Обновить запись в базе данных
 //public function updArtist($id, $name, $genre){
 //$stmt = $this->con->prepare("UPDATE artists SET name = '$name', genre = '$genre' WHERE id = '$id'");
 //$stmt->bind_param("ss", $name, $genre);
 //if($stmt->execute())
 //return true; 
 //return false; 
 //}
 
 //Извлечение всех записей из базы данных
 public function getCountries(){
 $stmt = $this->con->prepare("SELECT country_id, title_ru FROM countries");
 $stmt->bind_result($country_id, $title_ru);
 $stmt->execute();
 $countries = array();
 
 while($stmt->fetch()){
 $temp = array(); 
 $temp['country_id'] = $country_id; 
 $temp['title_ru'] = $title_ru; 
 array_push($countries, $temp);
 }
 return $countries;
 }
}