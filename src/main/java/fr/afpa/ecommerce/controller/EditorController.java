package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Editor;
import fr.afpa.ecommerce.model.EditorModel;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @editor Riad YOUSFI
 */
@WebServlet(name = "EditorController", urlPatterns = {"/Admin/Editor"})
public class EditorController extends HttpServlet {

    private final String editorForm = "/WEB-INF/admin/EditorForm.jsp";
    private final String editorIndex = "/WEB-INF/admin/EditorIndex.jsp";
    private EditorModel editorModel;

    @Override
    public void init() throws ServletException {
        super.init();
        editorModel = new EditorModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String action = request.getParameter("action");

            if (action == null) {
                action = "index";
            }

            switch (action) {
                case "add":
                    request.getRequestDispatcher(editorForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Editor editor = editorModel.find(id);
                        if (editor != null) {
                            request.setAttribute("editor", editor);
                            request.getRequestDispatcher(editorForm).forward(request, response);
                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "L'editeur que vous recherchez n'existe pas"));
                        }
                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "L'editeur que vous recherchez n'existe pas"));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    editorModel.delete(id);
                    goIndex(request, response, new Alert("Editeur supprimé avec succes"));
                    break;
                case "index":
                    goIndex(request, response, null);
                    break;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String name = request.getParameter("name").trim();

        Editor editor = new Editor();
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            editor.setId(id);
        } catch (NumberFormatException e) {
        }


        try {
            Validator.required(name);
            editor.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }
        try {
            if (!errors.isEmpty()) {
                request.setAttribute("editor", editor);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(editorForm).forward(request, response);

            } else {
                Alert alert;
                if (id != null) {
                    editorModel.update(editor);
                    alert = new Alert("Editeur modifié avec succes");
                } else {
                    editorModel.save(editor);
                    alert = new Alert("Editeur ajouté avec succes");
                }
                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Editor> editors = new ArrayList<>();

        try {
            editors = editorModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("alert", new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

        request.setAttribute("alert", alert);
        request.setAttribute("editors", editors);
        request.getRequestDispatcher(editorIndex).forward(request, response);
    }

}
