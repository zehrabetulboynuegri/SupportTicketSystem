Default user password ile yada datasql icerisindeki olusturulan kullanicilar ile giris yapabilirsiniz. 

Default Users => {
  1=> username : Admin,  password : nimda, role : (admin)
  2=> username : Zehra,  password : arhez, role : (user)
 }
 
O kullanıcılar dışında datasql(supportTicketTystem/src/main/resources/data.sql) içerisinde Admin kullanicisi için maili 'admin@gmail.com' password'u 'admin' olarak girebilirsiniz.

Aşağıda örnek user'ları görebilirsiniz.

INSERT INTO user (id, email, password, name) VALUES 
(1, 'admin@gmail.com', '{bcrypt}$2a$10$.xuUgiYgl.mqqJ3hMOYUteFiwyDJc59qUoMN93.FM6qKZOL4rvEJG', 'Admin'),
(2, 'deneme@gmail.com', '{bcrypt}$2a$10$i9nUOsXSC7uIuvohkjycTOstD1/TMwLSDIl9a5.vGqypdXtRyL7Gy', 'deneme'),
(3, 'betul@gmail.com', '{bcrypt}$2a$10$DA/Hz9AU.xI8M7mFPYGzGO7KCawdXSkYYl2nVIUUXACSy7//mnOL6', 'betul');


Ya da registerdan user rolünde yeni kullanıcı oluştururabilir, oluşturulan kullanıcı ile giriş sağlayabilirsiniz.

