
--
-- Table structure for table `student`
--
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_branch` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Sample data for table `student`
--

INSERT INTO `student` (`student_id`, `student_branch`, `student_email`, `student_name`) VALUES
(2, 'MS', 'abc@gmail.com', 'Abc'),
(3, 'BS', 'def@gmail.com', 'Def');
