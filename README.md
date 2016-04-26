# RecoinRestController
A restful api for interacting with Social-Computer project "TwitterCrowdSourcingController" https://github.com/Social-Computer/TwitterCrowdSourcingController

There are three main components of TwitterCrowdSourcingController: Project - Task and TaskRun.

## Documentation

All responses are Json objects.


http://domain.org/RecoinRestController/Project/
```
{
  "offset": 0,
  "project_list": [
    {
      "project_type": "validate",
      "bin_id": "health",
      "project_id": project_id,
      "identifiers": [
        "project_name"
      ],
      "project_status": "ready",
      "_id": {
        
      },
      "project_name": "project_name",
      "project_start_timestamp": "",
      "project_end_timestamp": "",
      "observed": "2016-04-08 15:35:43"
    },
    ...
	...
  ]
}
```

http://domain.org/RecoinRestController/Project/{id}

There are two additional fields added to this call: "tasks_count" and "taskRuns_count".

http://domain.org/RecoinRestController/Task/
```
{
  "offset": 200,
  "tasks": [
    {
      "bin_id_String": "57077f42e4b018844a579818",
      "task_status": "ready",
      "publishedAt": "2016-04-13 22:22:47",
      "twitter_lastPushAt": "2016-04-23 22:19:17",
      "twitter_task_status": "pushed",
      "task_id": task_id,
      "priority": 0,
      "media_url": "",
      "facebook_task_status": "pushed",
      "task_text": "some text",
      "facebook_lastPushAt": "2016-04-23 17:21:12",
      "embed_nomedia": {
        
      },
      "project_id": project_id,
      "facebook_task_id": "facebook_task_id",
      "_id": {
		
      },
      "embed": {
        
      },
      "task_type": "validate",
      "twitter_url": "twitter_url"
    },
	...
	...

  ]
}
```

http://domain.org/RecoinRestController/Task/project?project_id={id}

http://domain.org/RecoinRestController/Task/{id}

http://domain.org/RecoinRestController/Task/{id}/Responses

http://domain.org/RecoinRestController/TaskRun
```
{
  "taskRuns": [
    {
      "task_run_id": number,
      "project_id": number,
      "publishedAt": "2016-04-14 16:34:44",
      "contributor_name": "contributor_name",
      "task_id": number,
      "_id": {
		
      },
      "source": "Twitter",
      "task_run_text": "task_run_text",
      "wasProcessed": true
    },
	...
	...
  ],
  "offset": 0
}
```

http://domain.org/RecoinRestController/TaskRun/project?project_id={id}


http://domain.org/RecoinRestController/getTasks

This will return the top tasks without any taskruns, for there are none, then it returns task that are not yet completed


http://domain.org/RecoinRestController/sendTaskRun?text={text}&task_id={task_id}&project_id={project_id}&contributor_name={contributor_name}&source={source}

This sends a taskRun to the database. All fields must be provided.


A response with 200 indicates a successful operation. 500 code for any general errors (internal or within the rest api). 400 code for duplicate insertion.

If there are no records matching the request, response will be  {"message":""}

### Pagination

All request are given a default value of limit=200 and can be overridden alongside with offset. For example, http://domain.org/RecoinRestController/Project?limit={limit}&offset={offset}
Note that with each Json response there is a field "offset" that indicates whether you need to make another request with the given "offset" field value. A value of zero indicates that the complete list is retrieved. 

## Acknowledgements

This work is supported by the Lloyd's Register Foundation under the RECOIN project grant.


