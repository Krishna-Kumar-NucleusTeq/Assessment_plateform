package com.krishna.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a report.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "reportSeq",
initialValue = Report.ID_INITIAL_VALUE, allocationSize = 1)
public class Report {

    /**
     * Constant for initial value of report ID sequence. m
     */
    public static final int ID_INITIAL_VALUE = 6010;

    /**
     * The ID of the report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeq")
    private int reportId;

    /**
     * userName name attribute.
     */
//    private int userId;

    @Column(nullable = false)
    private String userEmailId;


    /**
     * quiz name attribute.
     */
    @Column(nullable = false)
    private int quizId;

    /**
     * total marks of quiz attribute.
     */
    @Column(nullable = false)
    private Integer totalMarks;

    /**
     * marks obtained in quiz attribute.
     */
    @Column(nullable = false)
    private int marksObtained;

    /**
     * total questions in quiz attribute.
     */
    @Column(nullable = false)
    private int totalQuestions;

    /**
     * number of attempted questions attribute.
     */
    @Column(nullable = false)
    private int attemptedQuestions;

    /**
     * date and time attribute.
     */
    @Column(nullable = false)
    private String dateAndTime;

    /**
     * The count of wrong answers in the assessment.
     */
    @Column(nullable = false)
    private int wrongAnswers;

}
