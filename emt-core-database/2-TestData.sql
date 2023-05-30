
--

LOCK TABLES `clinical_history_format` WRITE;
/*!40000 ALTER TABLE `clinical_history_format` DISABLE KEYS */;
INSERT INTO `clinical_history_format` VALUES (5,'Historia clínica general ','Historia clínica que contiene información general',1,'2022-11-29 20:25:11','[{\"id\":\"e18d15ee-173c-4dc1-9fd4-0461b47ab530\",\"content\":\"Describa el motivo de consulta de paciente \",\"questionIndex\":1,\"type\":\"STRING\",\"answers\":[{\"id\":\"5a3d02f4-0af9-4eae-bab3-a21be40cc9e7\",\"content\":\"\"}]},{\"id\":\"d639ef2b-d0c1-42ac-9c89-308147b6ef93\",\"content\":\"¿Cuáles de las siguientes enfermedades ha tenido el paciente?\",\"questionIndex\":2,\"type\":\"CHECKBOX\",\"answers\":[{\"id\":\"5a3d02f4-0af9-4eae-bab3-a21be40cc9e7\",\"content\":\"Asma\"},{\"id\":\"feba54c3-5778-4c19-9824-1d7f5e285a78\",\"content\":\"Diabetes \"},{\"id\":\"293c51dc-586b-41a5-bc2c-cf7c244fda16\",\"content\":\"Leucemia\"},{\"id\":\"737c0bd0-2928-49e1-b115-ac78fd372ace\",\"content\":\"Ninguna de las anteriores \"}]},{\"id\":\"00cc4000-3970-48e3-ace8-d014d633fe78\",\"content\":\"Seleccione cada cuánto el paciente se realiza exámenes médicos \",\"questionIndex\":3,\"type\":\"RADIO\",\"answers\":[{\"id\":\"5a3d02f4-0af9-4eae-bab3-a21be40cc9e7\",\"content\":\"1 vez al año\"},{\"id\":\"50c4c286-aefb-4cdb-ad61-92ce89e69790\",\"content\":\"Entre 2 a 4 veces al año \"},{\"id\":\"229540b3-dc56-4cad-ae62-e47d989628a3\",\"content\":\"Mensualmente \"},{\"id\":\"2855f4f4-6d5e-4414-84e3-7eca3a3cf989\",\"content\":\"No se realiza chequeos \"}]},{\"id\":\"5e552771-4399-4cc9-8fea-0d9685459424\",\"content\":\"¿Con que tipo de grupo se identifica más el paciente?\",\"questionIndex\":4,\"type\":\"SELECT\",\"answers\":[{\"id\":\"5a3d02f4-0af9-4eae-bab3-a21be40cc9e7\",\"content\":\"Activo\"},{\"id\":\"5a327846-f256-419e-8663-b3c257338ff0\",\"content\":\"Sedentario\"},{\"id\":\"5311eea1-52bc-40b8-8ad3-a70dafb4214c\",\"content\":\"Deportista\"}]}]'),(6,'Historia clinica de urgencias','Esta historia es para....',1,'2022-11-29 23:27:56','[{\"id\":\"4f1dc2e4-0952-4f58-a07a-231396195a57\",\"content\":\"Check\",\"questionIndex\":1,\"type\":\"CHECKBOX\",\"answers\":[{\"id\":\"2cae3021-68e0-43f8-9ad0-edebfca3ae49\",\"content\":\"op1\"},{\"id\":\"6dcec9a9-6112-43aa-8643-3a79a7275afa\",\"content\":\"op2\"}]},{\"id\":\"dc39c886-6977-4771-9850-8ab9a3192e1d\",\"content\":\"Genero\",\"questionIndex\":2,\"type\":\"SELECT\",\"answers\":[{\"id\":\"2cae3021-68e0-43f8-9ad0-edebfca3ae49\",\"content\":\"Masculino\"},{\"id\":\"f5c7ac2c-b8d2-4e41-b7fa-c783020e9301\",\"content\":\"Femenino\"}]}]');
/*!40000 ALTER TABLE `clinical_history_format` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('123446678','2022-11-29 20:20:23','Colombiana ',3);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_information`
--

LOCK TABLES `personal_information` WRITE;
/*!40000 ALTER TABLE `personal_information` DISABLE KEYS */;
INSERT INTO `personal_information` VALUES ('1007379759','Cristhian Camilo','Gutierrez Cordoba','kamneklogs@gmail.com','2000-11-18 05:00:00',2,1,'3178981818','Cl. 18 #122-135'),('1010138801','Andrea','Nuñez Rodriguez','andrea.nr2000@gmail.com','2000-12-19 05:00:00',1,1,'3182349189','Diagonal 4 #15-77'),('123446678','Paola','Osorio','','1994-02-22 05:00:00',1,2,'',''),('222','Andrea','Nuñez Rodriguez','andrea.nr2000@gmail.com','2000-12-19 05:00:00',1,1,'33','Dif');
/*!40000 ALTER TABLE `personal_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN','Administrador','Este rol pertenece a los usuarios con permiso de administración de la plataforma EMT-SYSTEM'),('administrative_staff','Personal administrativo','Este rol pertenece al area administrativa de la IPS'),('medical_staff','Personal médico','Este rol pertenece a los profesionales de la salud que se encargan de la atención de los pacientes'),('nursing_staff','Personal de enfermería','Este rol pertenece a todo el personal de enfermería de la institución');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1007379759','$2a$10$elCMcwd2Re6dimcvvuddz.SyLqjpcuM9VdBWjZ9B.YqBPDCxeErDC','2022-12-10 21:13:11',1),('1010138801','$2a$10$b93A5QSc7KAQMZoW7coGC.n6uOgj68U5/lTfnLLZDBYKuPDnmtB5C','1970-01-01 05:00:01',1),('admin','$2a$10$b93A5QSc7KAQMZoW7coGC.n6uOgj68U5/lTfnLLZDBYKuPDnmtB5C','2022-12-11 02:39:34',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('admin','ADMIN'),('1007379759','medical_staff'),('1010138801','medical_staff');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-10 21:43:16
