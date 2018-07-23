package component;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

public class Batch {
	/** 非同期処理するようにしたJobLuncher */
	@Autowired
	JobLauncher jobLauncher;

	/** 処理を定義したジョブ */
	@Autowired
	Job job;

	public void execute(Long orderId) throws Exception{
	    // 引数を定義する
	    JobParameters jobParameters = new JobParametersBuilder().addLong(
	         "order.id", orderId, true).toJobParameters();
	    // 非同期処理　開始！
	    jobLauncher.run(job, new JobParameters());
	}
}
