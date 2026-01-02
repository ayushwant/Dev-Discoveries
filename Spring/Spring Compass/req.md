1. user uploads a large xlsx file (e.g., 100MB) and the file reaches the backend service.
2. an entity process is created, and the file is stored in the attachment table. 
3. If the file is not an xlsx file, the user is returned an error message: "File type not supported. Please upload a valid xlsx file."
4. the user is returned a response with the entity process id with message: "File uploaded successfully. Adjustment will now begin."
5. The user can track the progress of the adjustment process in the process monitor, against the entity process id. They can also download their file from the attachment table, to check if the file is correct.
6. The backend service starts processing the file in an @Async operation.
   
7. In the @Async operation:
   - The file is fetched in an InputStream.
   - The InputStream is fed to OPCPackage, and XssfReader to read the xlsx file one row at a time.
   - We have a custom DefaultHandler implementation class that processes each row.
   - 