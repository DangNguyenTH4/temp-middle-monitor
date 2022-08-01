package com.sunteco.middlemonitor.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomQueryRequest extends BaseQueryRequest{
    private String queryTemplate;
    private String criteria;
}
