
while (true){
    Temperature = + (prompt("pls input the temperature"));
    if (Temperature>100){
        alert("pls reduce temperature")
        continue
    } else if (Temperature<20){
        alert("pls increase the temperature")
        continue
    }else{
        alert("control by your self ")
        break;
    }

} 