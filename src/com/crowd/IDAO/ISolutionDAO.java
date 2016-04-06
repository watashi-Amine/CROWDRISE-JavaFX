/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowd.IDAO;

import com.crowd.entities.Solution;
import java.util.List;

/**
 *
 * @author Oussama
 */
public interface ISolutionDAO {

    void addSolution(Solution solution);

    void updateSolution(Solution solution);

    void removeSolutionByAuthorId(int author_id);

    void removeSolutionById(int idsolution);

    void removeSolutionByIdProblemme(int idprobleme);

    List<Solution> findAll();

    Solution findSolutionById(int idsolution);

    Solution findSolutionByAuthorId(int author_id);

    Solution findSolutionByIdProblemme(int idprobleme);
}
