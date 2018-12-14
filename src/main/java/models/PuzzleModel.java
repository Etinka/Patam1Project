package models;

public class PuzzleModel {


    void connectToDbServer() {
        //TODO- add global db instance and initialize it
    }

    void addScore(int score, String name) {
    //TODO- write the current score to the database in the right place
    }

    void saveLevel(/*state?*/){
        //TODO- get the state and save lt to the database
    }

    void saveSettings(){
        //TODO- define what this func gets and what we are saving, maybe an object of settings?
    }

    void getLevelsByDifficulty(boolean isDesc) {
        //TODO- change the return type. return an array of levels in the right order
    }

    void getSolvedLevels(boolean user) {
        //TODO- change the return type and the boolean to user. get a specific user and returns all the levels that the user solved
    }

    void saveUserAchievements(boolean user) {
        //TODO- change the boolean to user type, saves the achievements of the user
    }

    void getBestUsersByTimeAndSteps(){
        //TODO- return the best users by time and steps
    }

   //TODO- create table in database- Users
    //id
    //userName

    //TODO- create table in database- Levels
    //id
    //levelData- the string of the level
    //difficulty

    //TODO- create table in database- Achievements
    //id
    //userId
    //levelId
    //solution time
    //solutionNumOfSteps

}

