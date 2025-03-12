/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

/**
 *
 * @author admin
 */
@WebServlet("/user")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // Giới hạn 5MB
public class User extends HttpServlet {
    private static final String ACCESS_TOKEN = "sl.u.AFm7yWS3KBpfKcjf1dnIZFiBeReqCenxlqdif-EWLJQXLydSgeGl_mUeuSyJoytln0v-68Nb3kzS1Gv-V8jSp1QKyJwM0sn0FKE0K9paUYV202nQVFXNwnSekNognHRnvAQZea5HfuzCvAW3MP18bBywicFPapWTBVUwqq7RfVpPx7SxIHTU0-LhN3c0r74BBW_5rMZjrOTwhEaCa3H-hANmT6t_eQiwUS0KPcwQ3BWHXqRuIFGnmvpn8dyZLrJ_f35IWZlU7y_ubCxiibU6QjDSzToaUI5knygG0GGx8KsVU88VqDSC6b2a8MzpF-s_soEZDozhQDV_tzkXo-W2s198Yuhw3RTzQyjcFkjVWGwrsu2RCNCQLdC8T26DHsPfGxJc--8Qjkcm7MsltrWv1qup9_bWmo58kjvO5NcMceOOuf9uj9WOYYEWfes1XP4kCBovqMfbPM17CilrprKHtZBpR_u00iL8Eet6iXOytbtDgc75xHHm9UhsTbKelPC6ZnOyJLHZYYUU6s_LYbHL4mYxSYBItpTHEt3OTkPceWpgOyct2EF_bApdPqICvI2ijTauQVlHf2dP9l4M2k0JrIc5wp6A2_25FqAx0u5-4AUdNPXCmkHPU02TdVBudQCDrw6N_BKgffz7-rAwoL9bayq6ztWGPMaMds_Y4K7IpNNHg1lDC7Pehx3XbsTnzdfcw2Ez_8jiGoFmbInTrpoBcz-E4u5OJMlNjlPFVyYN69XewQAWaKDH71hsaetWtkt20VV0zc1uJfk0A1xCjVJyQXyvsjBkdnoxVh1Uv5I9b2IvVv3SwxofOZZyspLGQIQbfI1g4C-OrUKfgjCjSBwrxz3kCueICyy9GXSyqc8wDCw3m1ci2UGqDJSrUKn3iF2apJjZe_oU1zZt0F6K9vlZWBtQUBHKVJiwmK5LCXvH7p7US9A8esxVdgWafJ3rcvt5j6H0tSa7BuFgCBrRgcQrxYOrfaD2EhxtW10dOGh0GQ-Dp7e1mbuXKFfLBLdjEsORul2q2JWpPMO40nQzw_uC1q_k9Q1a9tq1Qug5PpvtAKHaWLXxHP4uLxbX9zDlGi6halFQNaLo8gTVRSdA-mtDkb48YJDtJ_Mocv9e82kuO61GOuH-bRrd_492370Ff4V1y9j7VSHNoSNYl7VB9_2jl0k_BKBFRU7wIgqAb0am3Dazv9alVdumO0DKKRXEx5CBe4tnPYTOPTKKoHk3ouMdS5najBBAk4LpSh6MtP11sHbOLM7BkmVg73hWBW6NH6w1Qj3wctDVvSlL4JL-gdAjVFLd5Nr3PSkwHKBOyYF4cOkzOOFNrMqdp7YyJSRfQ0uqUNarMNxX0KVTs8w_y_OvtBDyieAqRfkPAMhqNV1NTnUz__faZyI4b9mv-WL483DWFqRZfV1aQl6bNcEr5otywdsU";
    private static final String DEFAULT_AVATAR_URL = "assets/default-ava.png";
    private static final String DB_URL = "jdbc:sqlserver://ND2P\\PHUONG:1433;databaseName=your_database;user=sa;password=123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String userId = request.getParameter("userId");

        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            // Lấy file từ request
            Part filePart = request.getPart("avatar");
            if (filePart == null || filePart.getSize() == 0) {
                response.getWriter().write("{\"message\": \"Chưa chọn file!\"}");
                return;
            }

            InputStream inputStream = filePart.getInputStream();
            String fileName = UUID.randomUUID().toString() + ".jpg"; // Tạo tên file ngẫu nhiên

            // Cấu hình Dropbox API
            DbxRequestConfig config = DbxRequestConfig.newBuilder("avatar-upload-app").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            // Upload file lên Dropbox
            FileMetadata metadata = client.files().uploadBuilder("/avatars/" + fileName)
                    .uploadAndFinish(inputStream);

            String avatarUrl = "https://www.dropbox.com/s/" + metadata.getId() + "?dl=1";

            // Cập nhật đường dẫn vào database
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET AvatarURL = ? WHERE UserID = ?")) {
                stmt.setString(1, avatarUrl);
                stmt.setString(2, userId);
                stmt.executeUpdate();
            }

            response.getWriter().write("{\"message\": \"Upload thành công!\", \"imageUrl\": \"" + avatarUrl + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String userId = request.getParameter("userId");

        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT AvatarURL FROM Users WHERE UserID = ?")) {

            stmt.setString(1, userId);
            var rs = stmt.executeQuery();

            if (!rs.next()) {
                response.getWriter().write("{\"message\": \"Không tìm thấy avatar!\"}");
                return;
            }

            String avatarUrl = rs.getString("AvatarURL");

            if (avatarUrl != null && !avatarUrl.equals(DEFAULT_AVATAR_URL)) {
                String dropboxFilePath = "/avatars/" + avatarUrl.substring(avatarUrl.lastIndexOf("/") + 1);

                // Cấu hình Dropbox API
                DbxRequestConfig config = DbxRequestConfig.newBuilder("avatar-upload-app").build();
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

                // Xoá file trên Dropbox
                DeleteResult deleteResult = client.files().deleteV2(dropboxFilePath);
            }

            // Cập nhật database về avatar mặc định
            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE Users SET AvatarURL = ? WHERE UserID = ?")) {
                updateStmt.setString(1, DEFAULT_AVATAR_URL);
                updateStmt.setString(2, userId);
                updateStmt.executeUpdate();
            }

            response.getWriter().write("{\"message\": \"Avatar đã được xoá!\", \"imageUrl\": \"" + DEFAULT_AVATAR_URL + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}