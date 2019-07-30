package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "six_w")
public class SixW {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 重点领域
     */
    @Column(name = "focus_areas")
    private String focusAreas;

    /**
     * 重点任务
     */
    @Column(name = "focus_task")
    private String focusTask;

    /**
     * 责任领导
     */
    @Column(name = "respon_leader")
    private String responLeader;

    /**
     * 牵头部门
     */
    @Column(name = "lead_branch")
    private String leadBranch;

    /**
     * 配合部门
     */
    @Column(name = "coop_branch")
    private String coopBranch;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 完成情况
     */
    @Column(name = "finish_state")
    private String finishState;

    /**
     * 完成情况评价
     */
    @Column(name = "finish_state_judge")
    private String finishStateJudge;

    /**
     * 责任专业
     */
    @Column(name = "respon_depart")
    private String responDepart;

    /**
     * 专业责任人
     */
    @Column(name = "respon_person")
    private String responPerson;

    /**
     * 任务内容
     */
    @Column(name = "task_content")
    private String taskContent;

    /**
     * 目标
     */
    private String target;

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取重点领域
     *
     * @return focus_areas - 重点领域
     */
    public String getFocusAreas() {
        return focusAreas;
    }

    /**
     * 设置重点领域
     *
     * @param focusAreas 重点领域
     */
    public void setFocusAreas(String focusAreas) {
        this.focusAreas = focusAreas;
    }

    /**
     * 获取重点任务
     *
     * @return focus_task - 重点任务
     */
    public String getFocusTask() {
        return focusTask;
    }

    /**
     * 设置重点任务
     *
     * @param focusTask 重点任务
     */
    public void setFocusTask(String focusTask) {
        this.focusTask = focusTask;
    }

    /**
     * 获取责任领导
     *
     * @return respon_leader - 责任领导
     */
    public String getResponLeader() {
        return responLeader;
    }

    /**
     * 设置责任领导
     *
     * @param responLeader 责任领导
     */
    public void setResponLeader(String responLeader) {
        this.responLeader = responLeader;
    }

    /**
     * 获取牵头部门
     *
     * @return lead_branch - 牵头部门
     */
    public String getLeadBranch() {
        return leadBranch;
    }

    /**
     * 设置牵头部门
     *
     * @param leadBranch 牵头部门
     */
    public void setLeadBranch(String leadBranch) {
        this.leadBranch = leadBranch;
    }

    /**
     * 获取配合部门
     *
     * @return coop_branch - 配合部门
     */
    public String getCoopBranch() {
        return coopBranch;
    }

    /**
     * 设置配合部门
     *
     * @param coopBranch 配合部门
     */
    public void setCoopBranch(String coopBranch) {
        this.coopBranch = coopBranch;
    }

    /**
     * 获取完成时间
     *
     * @return finish_time - 完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取完成情况
     *
     * @return finish_state - 完成情况
     */
    public String getFinishState() {
        return finishState;
    }

    /**
     * 设置完成情况
     *
     * @param finishState 完成情况
     */
    public void setFinishState(String finishState) {
        this.finishState = finishState;
    }

    /**
     * 获取完成情况评价
     *
     * @return finish_state_judge - 完成情况评价
     */
    public String getFinishStateJudge() {
        return finishStateJudge;
    }

    /**
     * 设置完成情况评价
     *
     * @param finishStateJudge 完成情况评价
     */
    public void setFinishStateJudge(String finishStateJudge) {
        this.finishStateJudge = finishStateJudge;
    }

    /**
     * 获取责任专业
     *
     * @return respon_depart - 责任专业
     */
    public String getResponDepart() {
        return responDepart;
    }

    /**
     * 设置责任专业
     *
     * @param responDepart 责任专业
     */
    public void setResponDepart(String responDepart) {
        this.responDepart = responDepart;
    }

    /**
     * 获取专业责任人
     *
     * @return respon_person - 专业责任人
     */
    public String getResponPerson() {
        return responPerson;
    }

    /**
     * 设置专业责任人
     *
     * @param responPerson 专业责任人
     */
    public void setResponPerson(String responPerson) {
        this.responPerson = responPerson;
    }

    /**
     * 获取任务内容
     *
     * @return task_content - 任务内容
     */
    public String getTaskContent() {
        return taskContent;
    }

    /**
     * 设置任务内容
     *
     * @param taskContent 任务内容
     */
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    /**
     * 获取目标
     *
     * @return target - 目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标
     *
     * @param target 目标
     */
    public void setTarget(String target) {
        this.target = target;
    }
}